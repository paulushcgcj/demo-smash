<script setup lang="ts">
import { Customer } from "../types/CustomerType";
const customer = defineModel();
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
              :model-value="(customer as Customer).firstName"
              @update:model-value="(customer as Customer).firstName = $event"
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Last name"
              :model-value="(customer as Customer).lastName"
              @update:model-value="(customer as Customer).lastName = $event"
              
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
              :model-value="(customer as Customer).email"
              @update:model-value="(customer as Customer).email = $event"
              
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Phone"
              :model-value="(customer as Customer).phoneNumber"
              @update:model-value="(customer as Customer).phoneNumber = $event"
              
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
              :model-value="(customer as Customer).addressLine1"
              @update:model-value="(customer as Customer).addressLine1 = $event"
              
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Address Line 2"
              :model-value="(customer as Customer).addressLine2"
              @update:model-value="(customer as Customer).addressLine2 = $event"
              
            />
          </cv-column>
        </cv-row>
        <cv-row>
          <cv-column>
            <cv-text-input 
            label="City" 
            :model-value="(customer as Customer).city"
            @update:model-value="(customer as Customer).city = $event"  
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Postal code"
              :model-value="(customer as Customer).postalCode"
              @update:model-value="(customer as Customer).postalCode = $event"
              
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="State"
              :model-value="(customer as Customer).state"
              @update:model-value="(customer as Customer).state = $event"
              
            />
          </cv-column>
          <cv-column>
            <cv-text-input
              label="Country"
              :model-value="(customer as Customer).country"
              @update:model-value="(customer as Customer).country = $event"
              
            />
          </cv-column>
        </cv-row>
      </cv-grid>
    </template>
  </cv-form-group>
</template>

<style scoped></style>
