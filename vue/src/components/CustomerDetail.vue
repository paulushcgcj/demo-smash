<script setup lang="ts">
import { PropType } from "vue";
import { Customer } from "../types/CustomerType";

defineProps({
  customer: {
    type: Object as PropType<Customer>,
    required: true,
  },
});
</script>

<template>
  <cv-form-group>
    <template #label>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <h3>Customer Details</h3>
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
    <template #content>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <cv-text-input
              label="First name"
              :model-value="customer.firstName"
              disabled
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Last name"
              :model-value="customer.lastName"
              disabled
            />
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
  </cv-form-group>

  <cv-form-group>
    <template #label>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <h3>Contact</h3>
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
    <template #content>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <cv-text-input
              label="Email"
              :model-value="customer.email"
              disabled
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Phone"
              :model-value="customer.phoneNumber"
              disabled
            />
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
  </cv-form-group>

  <cv-form-group>
    <template #label>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <h3>Location</h3>
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
    <template #content>
      <cv-grid :full-width="true" kind="wide">
        <cv-row>
          <cv-column>
            <cv-text-input
              label="Address Line 1"
              :model-value="customer.addressLine1"
              disabled
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Address Line 2"
              :model-value="customer.addressLine2"
              disabled
            />
          </cv-column>
        </cv-row>
        <cv-row>
          <cv-column>
            <cv-text-input label="City" :model-value="customer.city" disabled />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Postal code"
              :model-value="customer.postalCode"
              disabled
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="State"
              :model-value="customer.state"
              disabled
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Country"
              :model-value="customer.country"
              disabled
            />
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
  </cv-form-group>
</template>

<style scoped></style>
