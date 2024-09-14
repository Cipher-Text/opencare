import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import "./assets/css/index.css"
import "./assets/css/bootstrap.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import "./assets/css/react-calendar-picker.css"
import "./assets/css/react-date-picker.css"
import 'bootstrap/dist/css/bootstrap.min.css';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
