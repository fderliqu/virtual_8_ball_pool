package libs;

public enum BallTypeEnum {
        NULL,
        STRIPED,
        PLAIN,
        WHITE,
        BLACK;

        public static BallTypeEnum getOpposite(BallTypeEnum b) {
            return switch (b) {
                case PLAIN -> STRIPED;
                case STRIPED -> PLAIN;
                default -> NULL;
            };
        }
    }
