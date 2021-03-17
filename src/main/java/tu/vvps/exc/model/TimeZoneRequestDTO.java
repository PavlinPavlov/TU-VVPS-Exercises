package tu.vvps.exc.model;

public class TimeZoneRequestDTO {

    private String abbreviation;
    private String timezone;
    private String utcOffset;

    public static final String ABBREVIATION = "abbreviation";
    public static final String TIMEZONE = "timezone";
    public static final String UTC_OFFSET = "utc_offset";

    public TimeZoneRequestDTO(String abbreviation, String timezone, String utcOffset) {
        this.abbreviation = abbreviation;
        this.timezone = timezone;
        this.utcOffset = utcOffset;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    @Override
    public String toString() {
        return "TimeRequestZoneDTO{" +
                "abbreviation='" + abbreviation + '\'' +
                ", timezone='" + timezone + '\'' +
                ", utcOffset='" + utcOffset + '\'' +
                '}';
    }
}
