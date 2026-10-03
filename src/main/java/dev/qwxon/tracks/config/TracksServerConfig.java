package dev.qwxon.tracks.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class TracksServerConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue ENABLE_RENDER_TUNING_CHEATS;

    // === Physics: Tracks ===
    public static final ModConfigSpec.DoubleValue TRACK_STRENGTH_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue TRACK_TORQUE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue TRACK_SPEED_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue TRACK_GRIP_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue TRACK_SPRING_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue TRACK_DAMPING_MULTIPLIER;

    // === Physics: Offroad Wheels (WheelMount) ===
    public static final ModConfigSpec.DoubleValue WHEEL_TORQUE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue WHEEL_GRIP_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue WHEEL_SPRING_MULTIPLIER;

    public static boolean renderTuningCheatsEnabled() {
        return ENABLE_RENDER_TUNING_CHEATS.get();
    }

    public static double trackStrengthMultiplier() {
        try {
            return TRACK_STRENGTH_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double trackTorqueMultiplier() {
        try {
            return TRACK_TORQUE_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double trackSpeedMultiplier() {
        try {
            return TRACK_SPEED_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double trackGripMultiplier() {
        try {
            return TRACK_GRIP_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double trackSpringMultiplier() {
        try {
            return TRACK_SPRING_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double trackDampingMultiplier() {
        try {
            return TRACK_DAMPING_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double wheelTorqueMultiplier() {
        try {
            return WHEEL_TORQUE_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double wheelGripMultiplier() {
        try {
            return WHEEL_GRIP_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    public static double wheelSpringMultiplier() {
        try {
            return WHEEL_SPRING_MULTIPLIER.get();
        } catch (Exception e) {
            return 1.0;
        }
    }

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("server");
        ENABLE_RENDER_TUNING_CHEATS = builder
                .comment("Allows operators to open the in-game tracks render tuning menu with J. Disabled by default.")
                .define("enableRenderTuningCheats", false);
        builder.pop();

        builder.push("physics");
        builder.comment("Global physics multipliers for tracks and wheels.",
                "Increase these to make tracks stronger, faster, or grippier.",
                "1.0 = vanilla/default behavior. 2.0 = twice as strong/fast. 0.5 = half.");

        builder.push("tracks");
        TRACK_STRENGTH_MULTIPLIER = builder
                .comment("Global multiplier for track suspension strength (effectiveStrength).",
                        "Higher = can support heavier contraptions, less sagging, stronger spring overall.",
                        "トラックのサスペンション強度全体にかかる倍率。重い車体を支えたい場合に上げる。",
                        "Range: 0.1 ~ 20.0, Default: 1.0")
                .defineInRange("strengthMultiplier", 1.0, 0.1, 20.0);

        TRACK_TORQUE_MULTIPLIER = builder
                .comment("Global multiplier for track TORQUE / drive force (pushing power).",
                        "Higher = stronger pushing power, better climbing, better acceleration under load.",
                        "トルク（駆動力・押す力）にかかる倍率。坂道や重い車体でパワー不足を感じる場合に上げる。",
                        "This multiplies: kineticSpeed * -0.45 * partDriveMultiplier * perTrackDriveMultiplier",
                        "Range: 0.1 ~ 20.0, Default: 1.0")
                .defineInRange("torqueMultiplier", 1.0, 0.1, 20.0);

        TRACK_SPEED_MULTIPLIER = builder
                .comment("Global multiplier for track movement speed derived from RPM.",
                        "Higher = faster top speed at the same rotational speed (RPM).",
                        "移動速度（RPMから変換される速度）にかかる倍率。同じ回転数でより速く走りたい場合に上げる。",
                        "This multiplies the kineticSpeed contribution directly.",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("speedMultiplier", 1.0, 0.1, 10.0);

        TRACK_GRIP_MULTIPLIER = builder
                .comment("Global multiplier for track lateral/side grip.",
                        "Higher = less sideways sliding, better cornering.",
                        "横方向のグリップにかかる倍率。横滑りしやすい場合に上げる。",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("gripMultiplier", 1.0, 0.1, 10.0);

        TRACK_SPRING_MULTIPLIER = builder
                .comment("Global multiplier for track spring stiffness.",
                        "Higher = stiffer suspension.",
                        "スプリングの硬さにかかる倍率。",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("springMultiplier", 1.0, 0.1, 10.0);

        TRACK_DAMPING_MULTIPLIER = builder
                .comment("Global multiplier for track suspension damping.",
                        "Higher = more damping (less bouncing).",
                        "ダンパー（減衰）にかかる倍率。",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("dampingMultiplier", 1.0, 0.1, 10.0);
        builder.pop();

        builder.push("wheels");
        WHEEL_TORQUE_MULTIPLIER = builder
                .comment("Global multiplier for offroad wheel (WheelMount) drive force.",
                        "オフロードホイールの駆動力倍率。",
                        "Range: 0.1 ~ 20.0, Default: 1.0")
                .defineInRange("torqueMultiplier", 1.0, 0.1, 20.0);

        WHEEL_GRIP_MULTIPLIER = builder
                .comment("Global multiplier for offroad wheel grip.",
                        "オフロードホイールのグリップ倍率。",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("gripMultiplier", 1.0, 0.1, 10.0);

        WHEEL_SPRING_MULTIPLIER = builder
                .comment("Global multiplier for offroad wheel spring stiffness.",
                        "オフロードホイールのスプリング倍率。",
                        "Range: 0.1 ~ 10.0, Default: 1.0")
                .defineInRange("springMultiplier", 1.0, 0.1, 10.0);
        builder.pop();

        builder.pop();

        SPEC = builder.build();
    }
}
