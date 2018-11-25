package de.stoxygen;

import de.stoxygen.configuration.StoxygenNotifierConfiguration;
import de.stoxygen.model.NotificationSeverity;
import de.stoxygen.model.UserNotificationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RestfulClient {
    private static final Logger logger = LoggerFactory.getLogger(RestfulClient.class);

    @Autowired
    private StoxygenNotifierConfiguration stoxygenNotifierConfiguration;

    RestTemplate restTemplate;

    public RestfulClient() {
        restTemplate = new RestTemplate();
    }

    public List<UserNotificationData> getUserNotificationData(String username, NotificationSeverity notificationSeverity) {
        List<UserNotificationData> list;
        String url;
        url = stoxygenNotifierConfiguration.getUserServiceUrl() + "/api/v1/user/" + username + "/notification/severity/"
                + notificationSeverity.toString();
        logger.debug("Begin /GET request to {}", url);

        UserNotificationData[] data = restTemplate.getForObject(url, UserNotificationData[].class);
        list = Arrays.asList(data);


        return list;
    }
}
