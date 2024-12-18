<script setup lang="ts">
// vue
import { ref, onMounted } from "vue";
// Carbon
import { Edit20, Save20 } from "@carbon/icons-vue";
// Other components
import * as jsonpatch from "fast-json-patch";
// Smash components
import useAxios from "@/composables/useAxios";
import CustomerDetail from "@/components/CustomerDetail.vue";
import CustomerEdit from "@/components/CustomerEdit.vue";
import { Customer } from "@/types/CustomerType";

const props = defineProps({
  id: String,
});

const { loading, error, get, patch } = useAxios();

const isEditMode = ref<boolean>(false);
const data = ref<Customer>({} as Customer);
const originalItem = ref<Customer>({} as Customer);

const toggleEditMode = () => {
  isEditMode.value = !isEditMode.value;
  if (isEditMode.value) {
    originalItem.value = { ...data.value };
  } else {
    originalItem.value = {} as Customer;
  }
};

const fetchData = async () => {
  try {
    const response = await get<any>(`/api/customers/${props.id}`);
    data.value = response.data;
  } catch (err) {
    console.error(err);
  }
};

const saveData = async () => {
  const patchDocument = jsonpatch.compare(originalItem.value, data.value);
  console.log("save data    ", data.value);
  console.log("original data", originalItem.value);
  console.log("patchDocument", patchDocument);

  try {
    const response = await patch<any>(
      `/api/customers/${props.id}`,
      patchDocument
    );
    console.log(`response`, response);
    await fetchData();
  } catch (err) {
    console.error(err);
  }

  // save data
  toggleEditMode();
};

onMounted(() => {
  fetchData();
});
</script>

<template>
  <div>
    <cv-loading :active="loading" overlay small />
    <cv-inline-notification
      kind="error"
      actionLabel=""
      hideCloseButton
      v-if="error"
    >
      <template #title>
        <h2>Error during request</h2>
      </template>
      <template #subtitle>
        {{ error }}
      </template>
    </cv-inline-notification>

    <CustomerDetail :customer="data" v-if="!isEditMode" />
    <CustomerEdit v-model="data" v-else />
    <cv-button-set>
      <cv-button
        :icon="Edit20"
        @click="toggleEditMode"
        :aria-label="isEditMode ? 'cancel button' : 'edit button'"
        size="field"
        kind="secondary"
        >{{ isEditMode ? "Cancel Edit" : "Edit" }}
      </cv-button>
      <cv-button
        v-if="isEditMode"
        :icon="Save20"
        kind="primary"
        @click="saveData"
        aria-label="save button"
        size="field"
        >Save
      </cv-button>
    </cv-button-set>
  </div>
</template>

<style scoped></style>
