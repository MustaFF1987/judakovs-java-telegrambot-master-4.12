package ebe.P_Judakov.s.JAVABOT.domen.entity.interfaces;

public interface SubscribedChannel {
        int id();
        long chatId();
        long channelId();
        String channelTitle();
        int lastArticleId();
        long getChatId();

        void setChannelTitle(String channelTitle);
        void setChatId(long chatId);
        void setLastArticleId(int lastArticleId);

        Object getId();
}


