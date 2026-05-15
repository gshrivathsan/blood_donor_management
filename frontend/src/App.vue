<template>
  <div class="app">
    <div v-if="!isLoggedIn" class="login-container">
      <div class="login-card">
        <h1>Blood Bank Admin Portal</h1>
        <p class="subtitle">Login to manage donors and monitor dashboard statistics</p>

        <input v-model="login.username" placeholder="Username" />
        <input v-model="login.password" type="password" placeholder="Password" />

        <button @click="loginUser">Login</button>

        <p class="hint">
          Demo Credentials → admin / admin
        </p>
      </div>
    </div>

    <div v-else class="dashboard">
      <aside class="sidebar">
        <h2>Blood Bank</h2>

        <ul>
          <li :class="{active: currentPage === 'dashboard'}"
              @click="currentPage = 'dashboard'">
            Dashboard
          </li>

          <li :class="{active: currentPage === 'donors'}"
              @click="currentPage = 'donors'">
            Donors
          </li>
        </ul>

        <button class="logout-btn" @click="logout">
          Logout
        </button>
      </aside>

      <main class="content">
        <div v-if="currentPage === 'dashboard'">
          <h1>Admin Dashboard</h1>

          <div class="stats-grid">
            <div class="stat-card">
              <h3>Total Donors</h3>
              <p>{{ stats.donors || 0 }}</p>
            </div>

            <div class="stat-card">
              <h3>Total Hospitals</h3>
              <p>{{ stats.hospitals || 0 }}</p>
            </div>

            <div class="stat-card">
              <h3>Total Requests</h3>
              <p>{{ stats.requests || 0 }}</p>
            </div>
          </div>
        </div>

        <div v-if="currentPage === 'donors'">
          <div class="top-bar">
            <h1>Donor Management</h1>
          </div>

          <div class="card">
            <h2>Add Donor</h2>

            <div class="form-grid">
              <input v-model="donor.name" placeholder="Name" />
              <input v-model="donor.bloodGroup" placeholder="Blood Group" />
              <input v-model="donor.city" placeholder="City" />
              <input v-model="donor.phone" placeholder="Phone" />
            </div>

            <button @click="addDonor">
              Save Donor
            </button>
          </div>

          <div class="card">
            <h2>Donor List</h2>

            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Blood Group</th>
                  <th>City</th>
                  <th>Phone</th>
                </tr>
              </thead>

              <tbody>
                <tr v-for="d in donors" :key="d.id">
                  <td>{{ d.name }}</td>
                  <td>{{ d.bloodGroup }}</td>
                  <td>{{ d.city }}</td>
                  <td>{{ d.phone }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const isLoggedIn = ref(false)
const currentPage = ref('dashboard')

const login = ref({
  username: '',
  password: ''
})

const donors = ref([])
const stats = ref({})

const donor = ref({
  name: '',
  bloodGroup: '',
  city: '',
  phone: ''
})

const loginUser = async () => {
  if (login.value.username === 'admin' &&
      login.value.password === 'admin') {

    isLoggedIn.value = true
    await loadData()
  } else {
    alert('Invalid credentials')
  }
}

const logout = () => {
  isLoggedIn.value = false
}

const loadData = async () => {
  try {
    const donorsResponse = await axios.get('/api/donors')
    donors.value = donorsResponse.data

    const statsResponse = await axios.get('/api/dashboard/stats')
    stats.value = statsResponse.data
  } catch (error) {
    console.error(error)
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

    alert('Donor added successfully')
  } catch (error) {
    console.error(error)
  }
}

onMounted(loadData)
</script>

<style>
*{
  margin:0;
  padding:0;
  box-sizing:border-box;
}

body{
  font-family:Arial,sans-serif;
  background:#f4f6f9;
}

.login-container{
  height:100vh;
  display:flex;
  align-items:center;
  justify-content:center;
}

.login-card{
  width:400px;
  background:white;
  padding:40px;
  border-radius:14px;
  box-shadow:0 10px 30px rgba(0,0,0,0.1);
}

.login-card h1{
  margin-bottom:10px;
}

.subtitle{
  color:#666;
  margin-bottom:25px;
}

.login-card input{
  width:100%;
  padding:12px;
  margin-bottom:15px;
  border:1px solid #ccc;
  border-radius:8px;
}

.login-card button{
  width:100%;
  padding:12px;
  border:none;
  background:#d32f2f;
  color:white;
  border-radius:8px;
  cursor:pointer;
}

.hint{
  margin-top:15px;
  font-size:14px;
  color:#666;
}

.dashboard{
  display:flex;
  min-height:100vh;
}

.sidebar{
  width:240px;
  background:#b71c1c;
  color:white;
  padding:30px 20px;
}

.sidebar h2{
  margin-bottom:30px;
}

.sidebar ul{
  list-style:none;
}

.sidebar li{
  padding:14px;
  margin-bottom:10px;
  border-radius:8px;
  cursor:pointer;
}

.sidebar li:hover,
.sidebar .active{
  background:rgba(255,255,255,0.2);
}

.logout-btn{
  margin-top:30px;
  width:100%;
  padding:12px;
  border:none;
  border-radius:8px;
  cursor:pointer;
}

.content{
  flex:1;
  padding:30px;
}

.stats-grid{
  display:grid;
  grid-template-columns:repeat(auto-fit,minmax(220px,1fr));
  gap:20px;
  margin-top:25px;
}

.stat-card{
  background:white;
  padding:25px;
  border-radius:14px;
  box-shadow:0 4px 10px rgba(0,0,0,0.05);
}

.stat-card h3{
  margin-bottom:10px;
  color:#666;
}

.stat-card p{
  font-size:34px;
  font-weight:bold;
  color:#d32f2f;
}

.card{
  background:white;
  padding:25px;
  border-radius:14px;
  margin-top:20px;
  box-shadow:0 4px 10px rgba(0,0,0,0.05);
}

.form-grid{
  display:grid;
  grid-template-columns:repeat(2,1fr);
  gap:15px;
  margin:20px 0;
}

input{
  padding:12px;
  border:1px solid #ccc;
  border-radius:8px;
}

button{
  padding:12px 18px;
  background:#d32f2f;
  color:white;
  border:none;
  border-radius:8px;
  cursor:pointer;
}

table{
  width:100%;
  border-collapse:collapse;
  margin-top:20px;
}

th,td{
  padding:14px;
  border-bottom:1px solid #ddd;
  text-align:left;
}

th{
  background:#f5f5f5;
}

@media(max-width:768px){
  .dashboard{
    flex-direction:column;
  }

  .sidebar{
    width:100%;
  }

  .form-grid{
    grid-template-columns:1fr;
  }
}
</style>
