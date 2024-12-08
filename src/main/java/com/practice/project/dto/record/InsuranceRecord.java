package com.practice.project.dto.record;

public record InsuranceRecord(String type, float premium) {
    private static final String DEFAULT_INSURANCE_TYPE = "Health Insurance";
    private static final float DEFAULT_PREMIUM = 1000;
    public static InsuranceRecord getDefaultInsurance() {
        return new InsuranceRecord(DEFAULT_INSURANCE_TYPE, DEFAULT_PREMIUM);
    }

    public static class PolicyValidator {
        public void validate(InsuranceRecord record) {
            if (record.premium() < 100) {
                System.out.println("Invalid premium: must be at least 100.");
            } else {
                System.out.println("Insurance record is valid.");
            }
        }
    }
}
