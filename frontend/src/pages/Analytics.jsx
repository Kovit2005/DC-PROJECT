import React, {useEffect, useState} from 'react'
import axios from 'axios'
import { Bar } from 'react-chartjs-2'

export default function Analytics(){
  const [data, setData] = useState({})

  useEffect(()=>{
    axios.get('/api/analytics/summary').then(r=>setData(r.data)).catch(()=>{})
  },[])

  const routes = Object.keys(data)
  const bookings = routes.map(r=>data[r].bookings)
  const revenue = routes.map(r=>data[r].revenue)

  const chartData = {
    labels: routes,
    datasets: [
      { label: 'Bookings', data: bookings, backgroundColor: 'rgba(99,102,241,0.8)' },
      { label: 'Revenue', data: revenue, backgroundColor: 'rgba(16,185,129,0.8)' }
    ]
  }

  return (
    <div>
      <h1 className="text-2xl mb-4">Analytics</h1>
      {routes.length===0 ? <div>No analytics yet</div> : <Bar data={chartData} />}
    </div>
  )
}
