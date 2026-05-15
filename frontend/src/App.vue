<template>
  <div class="container">
    <h1>Blood Donation Management Dashboard</h1>

    <div class="card">
      <h2>Dashboard Stats</h2>
      <p>Total Donors: {{ stats.donors }}</p>
      <p>Total Hospitals: {{ stats.hospitals }}</p>
      <p>Total Requests: {{ stats.requests }}</p>
    </div>

    <div class="card">
      <h2>Add Donor</h2>

      <input v-model="donor.name" placeholder="Name" />
      <input v-model="donor.bloodGroup" placeholder="Blood Group" />
      <input v-model="donor.city" placeholder="City" />
      <input v-model="donor.phone" placeholder="Phone" />

      <button @click="addDonor">Save</button>
    </div>

    <ul>
      <li v-for="d in donors" :key="d.id">
        {{ d.name }} - {{ d.bloodGroup }} - {{ d.city }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const donors = ref([])
const stats = ref({})
const donor = ref({
  name: '',
  bloodGroup: '',
  city: '',
  phone: ''
})

const loadData = async () => {
  try {
    const donorsResponse = await axios.get('/api/donors')
    donors.value = donorsResponse.data

    const statsResponse = await axios.get('/api/dashboard/stats')
    stats.value = statsResponse.data
  } catch (error) {
    console.error('Error loading data:', error)
  }
}

const addDonor = async () => {
  try {
    await axios.post('/api/donors', donor.value)

    donor.value = {
      name: '',
      bloodGroup: '',
      city: '',
      phone: ''
    }

    await loadData()
  } catch (error) {
    console.error('Error adding donor:', error)
  }
}

onMounted(loadData)
</script>

<style>
body {
  font-family: Arial, sans-serif;
  background: #f5f5f5;
  margin: 0;
  padding: 20px;
}

.container {
  width: 800px;
  margin: auto;
  background: white;
  padding: 20px;
  border-radius: 10px;
}

.card {
  padding: 15px;
  border: 1px solid #ddd;
  margin-bottom: 20px;
  border-radius: 8px;
}

input {
  display: block;
  width: 100%;
  padding: 8px;
  margin: 10px 0;
  box-sizing: border-box;
}

button {
  padding: 10px 16px;
  cursor: pointer;
}
</style>