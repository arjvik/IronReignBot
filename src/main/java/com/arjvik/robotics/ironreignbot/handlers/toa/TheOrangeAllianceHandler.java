package com.arjvik.robotics.ironreignbot.handlers.toa;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.arjvik.robotics.ironreignbot.handlers.AbstractMessageHandler;
import com.arjvik.robotics.ironreignbot.handlers.annotations.EventHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import discord4j.core.object.entity.Message;
import reactor.netty.http.client.HttpClient;

@EventHandler("!toa")
public class TheOrangeAllianceHandler extends AbstractMessageHandler {

	public static String TOA_KEY;

	private ObjectMapper mapper = new ObjectMapper();
	private HttpClient client = HttpClient.create()
										  .baseUrl("https://theorangealliance.org/api/")
										  .headers(h -> h.add("Accept", "application/json")
														 .add("Content-Type", "application/json")
														 .add("X-TOA-Key", TOA_KEY)
														 .add("X-Application-Origin", "ironreignbot"));


	@Override
	protected void onMessageEvent(Message msg, String content) {
		try {
			String[] cmd = content.split("\\s+");
			if (cmd.length == 1 && cmd[0].matches("\\d+")) {
				Team team = client.get()
								  .uri(String.format("/team/%s", cmd[0]))
								  .responseSingle((resp, cont) -> cont.asInputStream().map(c -> readValue(c, new TypeReference<List<Team>>() {})))
								  .map(l -> l.get(0))
								  .block();
				msg.getChannel().block().createEmbed(spec -> {
					spec.setTitle(String.format("FTC Team %s", team.getTeamNumber()));
					spec.addField("Location", String.format("%s, %s, %s", team.getCity(), team.getStateProv(), team.getCountry()), true);
					spec.addField("Rookie Year", String.format("%d", team.getRookieYear()), true);
					spec.addField("Last Active", String.format("20%s-20%s", team.getLastActive().substring(0, 2), team.getLastActive().substring(2)), true);
					spec.addField("Website", team.getWebsite(), true);
				}).block();
			} else {
				replyTo(msg, "Invalid usage of `!toa <team>`");
			}
		} catch (RuntimeException e) {
				replyTo(msg, "Error getting JSON from TOA API");
		}
	}

	private <T> T readValue(InputStream is, TypeReference<T> type) {
		try {
			return mapper.readValue(is, type);
		} catch (IOException e) {
				throw new RuntimeException(e);
		}
	}

}
