package com.unagit.nytimesbrowser.helpers;

import android.os.Build;

import com.unagit.nytimesbrowser.BuildConfig;

/**
 * Global constants, used for entire app.
 */

public final class Constants {

    public static final class Tabs {
        /* Number of tabs */
        public static final int TABS_COUNT = 3;

        /* Position of tabs on the screen */
        public static final int MOST_EMAILED_TAB = 0;
        public static final int MOST_SHARED_TAB = 1;
        public static final int MOST_VIEWED_TAB = 2;

        /* Tab titles */
        public static final String MOST_EMAILED_TITLE = "Most Emailed";
        public static final String MOST_SHARED_TITLE = "Most Shared";
        public static final String MOST_VIEWED_TITLE = "Most Viewed";
    }

    public static final class Retrofit {
        public static final String NYTApiKey = BuildConfig.NYTimesApiKey;
        public static final String NYTBaseApiUrl = "https://api.nytimes.com/svc/mostpopular/v2/";
    }

    public static final class Queries {
        public static final String queryLabel = "queryLabel";
    }

    public static final class IntentArticleExtras {
        public static final String articleUrl = "articleUrl";
    }

}
