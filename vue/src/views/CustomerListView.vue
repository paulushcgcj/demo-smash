<script setup lang="ts">
// Vue
import { ref, onMounted } from "vue";

//Carbon
import { 
  TrashCan20 as TrashCan, 
  View20 as View,
  Add20 as AddIcon,
  Save20 as SaveIcon
} from "@carbon/icons-vue";

// Smash Components
import useAxios from "@/composables/useAxios";
import CustomerEdit from "@/components/CustomerEdit.vue";
import { Customer } from "@/types/CustomerType";

const { loading, error, get, remove, post } = useAxios();

const data = ref<any[]>([]);
const isAddMode = ref<boolean>(false);
const newCustomerData = ref<Customer>({} as Customer);


const toggleAddMode = async () => {
  isAddMode.value = !isAddMode.value;  
  if (!isAddMode.value) {
    newCustomerData.value = {} as Customer;
    fetchData();
  }
};

const fetchData = async () => {
  try {
    const response = await get<any>("/api/customers");
    data.value = response.data;
  } catch (err) {
    console.error(err);
  }
};

const deleteData = async (id: string) => {
  try {
    await remove(`/api/customers/${id}`);
    fetchData();
  } catch (err) {
    console.error(err);
  }
};

const saveData = async () => {
  try {
    await post<any>("/api/customers", newCustomerData.value);
    toggleAddMode();
  } catch (err) {
    console.error(err);
  }
}

onMounted(() => {
  fetchData();
});
</script>

<template>
  <div v-if="!isAddMode">
    <cv-data-table-skeleton
      v-if="loading"
      :columns="3"
      :rows="10"
      title="Customers"
      helper-text="All active customers should be listed here."
    >
    </cv-data-table-skeleton>

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

    <cv-data-table
      title="Customers"
      :zebra="true"
      helper-text="All active customers should be listed here."
      :pagination="true"
      v-if="!loading"
    >
      <template #actions>
        <cv-data-table-action @click="toggleAddMode" aria-label="compile" alt="compile">
          <AddIcon>
            <title>Add customer</title>
          </AddIcon>
        </cv-data-table-action>        
      </template>

      <template #headings>
        <cv-data-table-heading
          id="sb-name"
          heading="Name"
          name="name"
          sortable
          order="ascending"
        />
        <cv-data-table-heading
          id="sb-email"
          heading="Email"
          name="email"
          sortable
        />
        <cv-data-table-heading id="sb-act" heading="Actions" />
      </template>

      <template #data>
        <cv-data-table-row
          v-for="customer in data"
          :id="customer.id"
          :key="customer.id"
          :value="customer.id"
        >
          <cv-data-table-cell
            >{{ customer.firstName }}
            {{ customer.lastName }}</cv-data-table-cell
          >
          <cv-data-table-cell>{{ customer.email }}</cv-data-table-cell>
          <cv-data-table-cell>
            <cv-grid>
              <cv-row kind="condensed">
                <cv-column :sm="0" :md="2" :lg="3">
                  <router-link
                    :to="{ name: 'Customer', params: { id: customer.id } }"
                  >
                    <View />
                  </router-link>
                </cv-column>
                <cv-column :sm="0" :md="2" :lg="3">
                  <cv-button 
                    :icon="TrashCan"
                    @click="deleteData(customer.id)"
                    kind="danger-ghost"
                    aria-label="button story"
                    size="field">
                  </cv-button>                  
                </cv-column>
              </cv-row>
            </cv-grid>
          </cv-data-table-cell>
        </cv-data-table-row>
      </template>
    </cv-data-table>
  </div>
  <div v-else>
    <CustomerEdit v-model="newCustomerData" />
    <cv-button
        :icon="SaveIcon"
        kind="primary"
        @click="saveData"
        aria-label="save button"
        size="field"
        >Save
      </cv-button>
  </div>
</template>

<style scoped></style>
