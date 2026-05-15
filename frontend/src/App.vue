
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
<input v-model="donor.name" placeholder="Name"/>
<input v-model="donor.bloodGroup" placeholder="Blood Group"/>
<input v-model="donor.city" placeholder="City"/>
<input v-model="donor.phone" placeholder="Phone"/>
<button @click="addDonor">Save</button>
</div>

<ul>
<li v-for="d in donors" :key="d.id">
{{d.name}} - {{d.bloodGroup}} - {{d.city}}
</li>
</ul>
</div>
</template>

<script setup>
import {ref,onMounted} from 'vue'
import axios from 'axios'

const donors = ref([])
const stats = ref({})
const donor = ref({name:'',bloodGroup:'',city:'',phone:''})

const loadData = async () => {
 donors.value = (await axios.get('http://localhost:8080/api/donors')).data
 stats.value = (await axios.get('http://localhost:8080/api/dashboard/stats')).data
}

const addDonor = async () => {
 await axios.post('http://localhost:8080/api/donors', donor.value)
 donor.value = {name:'',bloodGroup:'',city:'',phone:''}
 loadData()
}

onMounted(loadData)
</script>

<style>
body{font-family:Arial;background:#f5f5f5}
.container{width:800px;margin:auto;background:white;padding:20px}
.card{padding:15px;border:1px solid #ddd;margin-bottom:20px}
input{display:block;width:100%;padding:8px;margin:10px 0}
button{padding:10px 16px}
</style>
