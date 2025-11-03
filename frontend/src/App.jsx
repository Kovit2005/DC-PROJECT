import React from 'react'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Bookings from './pages/Bookings'
import Analytics from './pages/Analytics'

export default function App(){
  return (
    <BrowserRouter>
      <div className="min-h-screen bg-gray-900 text-white">
        <nav className="p-4 flex gap-4">
          <Link to="/" className="text-lg">Home</Link>
          <Link to="/bookings" className="text-lg">My Bookings</Link>
          <Link to="/analytics" className="text-lg">Analytics</Link>
        </nav>
        <main className="p-6">
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/bookings" element={<Bookings/>} />
            <Route path="/analytics" element={<Analytics/>} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  )
}
