package stepdefinitions;

public class PatientPayloadBuilder {

    private static final String OPENMRS_ID_TYPE_UUID = "05a29f94-c0ed-11e2-94be-8c13b969e334";
    private static final String IDENTIFIER_LOCATION_UUID = "8d6c993e-c2cc-11de-8d13-0010c6dffd0f";

    private static final String LUHN_MOD_30_CHARSET = "0123456789ACDEFGHJKLMNPRTUVWXY";

    private static String withCheckDigit(String undecoratedIdentifier) {
        int factor = 2;
        int sum = 0;
        int n = LUHN_MOD_30_CHARSET.length();
        for (int i = undecoratedIdentifier.length() - 1; i >= 0; i--) {
            int codePoint = LUHN_MOD_30_CHARSET.indexOf(undecoratedIdentifier.charAt(i));
            int addend = factor * codePoint;
            factor = (factor == 2) ? 1 : 2;
            addend = (addend / n) + (addend % n);
            sum += addend;
        }
        int remainder = sum % n;
        char checkDigit = LUHN_MOD_30_CHARSET.charAt((n - remainder) % n);
        return undecoratedIdentifier + checkDigit;
    }

    public static String build(String givenName, String familyName, String gender, String birthdate) {
        String identifier = withCheckDigit(String.valueOf(System.currentTimeMillis()));
        return """
                {
                  "identifiers": [
                    {
                      "identifier": "%s",
                      "identifierType": "%s",
                      "location": "%s",
                      "preferred": true
                    }
                  ],
                  "person": {
                    "names": [
                      { "givenName": "%s", "familyName": "%s" }
                    ],
                    "gender": "%s",
                    "birthdate": "%s"
                  }
                }
                """.formatted(identifier, OPENMRS_ID_TYPE_UUID, IDENTIFIER_LOCATION_UUID,
                givenName, familyName, gender, birthdate);
    }
}
