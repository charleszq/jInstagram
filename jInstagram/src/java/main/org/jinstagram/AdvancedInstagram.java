/**
 * 
 */
package org.jinstagram;

import java.util.HashMap;
import java.util.Map;

import org.jinstagram.auth.model.OAuthRequest;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.http.Response;
import org.jinstagram.http.Verbs;
import org.jinstagram.model.Methods;

/**
 * @author charles (charleszq@gmail.com)
 * 
 */
public class AdvancedInstagram extends Instagram {

	/**
	 * @param accessToken
	 */
	public AdvancedInstagram(Token accessToken) {
		super(accessToken);
	}

	/**
	 * @param clientId
	 */
	public AdvancedInstagram(String clientId) {
		super(clientId);
	}

	public MediaFeed getUserFeeds(int count) throws InstagramException {
		if (count <= 0) {
			return super.getUserFeeds();
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("count", String.valueOf(count));
		MediaFeed userFeed = this.createInstagramObject(Verbs.GET,
				MediaFeed.class, Methods.USERS_SELF_FEED, params);

		return userFeed;
	}

	public MediaFeed getPopularMedia(int count) throws InstagramException {
		if (count <= 0) {
			return super.getPopularMedia();
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("count", String.valueOf(count));
		MediaFeed userFeed = createInstagramObject(Verbs.GET, MediaFeed.class,
				Methods.MEDIA_POPULAR, params);

		return userFeed;
	}

	public MediaFeed getNextPage(Pagination pagination, int count)
			throws InstagramException {

		OAuthRequest request = new OAuthRequest(Verbs.GET,
				pagination.getNextUrl());
		request.addQuerystringParameter("count", String.valueOf(count));
		Response response = request.send();
		MediaFeed feed = createObjectFromResponse(MediaFeed.class,
				response.getBody());
		return feed;
	}

}
