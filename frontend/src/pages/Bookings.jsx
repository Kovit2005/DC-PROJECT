import React, {useEffect, useState} from 'react'
import axios from 'axios'

export default function Bookings(){
  const [userId, setUserId] = useState(1)
  const [bookings, setBookings] = useState([])

  useEffect(()=>{
    fetchBookings()
  },[])

  function fetchBookings(){
    axios.get(`/api/bookings/${userId}`).then(r=>setBookings(r.data)).catch(()=>{})
  }

  return (
    <div>
      <h1 className="text-2xl mb-4">My Bookings</h1>
      <label className="block mb-2">User ID: <input type="number" value={userId} onChange={e=>setUserId(Number(e.target.value))} className="ml-2 p-1 rounded text-black"/></label>
      <button onClick={fetchBookings} className="mb-4 px-3 py-1 bg-indigo-600 rounded">Load</button>
      <div className="space-y-3">
        {bookings.map(b=> (
          <div key={b.id} className="p-3 bg-gray-800 rounded">
            <div>Train: {b.train?.name}</div>
            <div>Seats: {b.seats}</div>
            <div>Total: {b.totalPrice}</div>
            <div>Booked At: {b.bookedAt}</div>
          </div>
        ))}
      </div>
    </div>
  )
}
