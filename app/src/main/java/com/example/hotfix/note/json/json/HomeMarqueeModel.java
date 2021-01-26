package com.example.hotfix.note.json.json;

public class HomeMarqueeModel {
    private MarqueeModel marquee;

    public static class MarqueeModel {
        private String content;
        private boolean status;

        public String getContent() {
            return content;
        }

        public boolean isStatus() {
            return status;
        }

        @Override
        public String toString() {
            return "MarqueeModel{" +
                    "content='" + content + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public MarqueeModel getMarquee() {
        return marquee;
    }

    @Override
    public String toString() {
        return "HomeMarqueeModel{" +
                "marquee=" + marquee +
                '}';
    }
}
