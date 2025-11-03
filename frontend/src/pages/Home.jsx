import React, {useEffect, useState} from 'react'
import axios from 'axios'

export default function Home(){
  const [trains, setTrains] = useState([])
  const [userId, setUserId] = useState(1)
  const [seats, setSeats] = useState(1)

  useEffect(()=>{
    axios.get('/api/trains').then(r=>setTrains(r.data)).catch(()=>{})
  },[])

  const book = (trainId)=>{
    axios.post('/api/book',{userId, trainId, seats}).then(()=>{
      alert('Booked')
    }).catch(e=>alert('Error: '+(e.response?.data?.message || e.message)))
  }

  return (
    <div>
      <h1 className="text-2xl mb-4">Available Trains</h1>
      <div className="space-y-4">
        <label className="block">User ID: <input type="number" value={userId} onChange={e=>setUserId(Number(e.target.value))} className="ml-2 p-1 rounded text-black"/></label>
        <label className="block">Seats: <input type="number" value={seats} onChange={e=>setSeats(Number(e.target.value))} className="ml-2 p-1 rounded text-black"/></label>
      {trains.map(t=> (
        <div key={t.id} className="p-4 bg-gray-800 rounded">
          <div className="flex justify-between">
            <div>
              <div className="font-bold">{t.name}</div>
              <div className="text-sm">{t.route}</div>
              <div className="text-xs">Seats available: {t.seatsAvailable}</div>
            </div>
            <div>
              <div className="mb-2">${t.price}</div>
              <button onClick={()=>book(t.id)} className="px-3 py-1 bg-indigo-600 rounded">Book</button>
            </div>
          </div>
        </div>
      ))}
      </div>
    </div>
  )
}
