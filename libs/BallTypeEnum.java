package libs;

public enum BallTypeEnum {
        NULL ("not assigned"),
        STRIPED ("striped"),
        PLAIN ("plain"),
        WHITE ("white"),
        BLACK ("black");

        private final String name;

        BallTypeEnum(String s) {
            name = s;
        }

        public static BallTypeEnum getOpposite(BallTypeEnum b) {
            return switch (b) {
                case PLAIN -> STRIPED;
                case STRIPED -> PLAIN;
                default -> NULL;
            };
        }

        public String toString() {
            return this.name;
        }
    }
