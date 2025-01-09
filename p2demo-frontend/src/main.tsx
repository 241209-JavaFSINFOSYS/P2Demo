import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { AuthProvider } from './Contexts/AuthContext.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    {/* Wrapping all of the components defined in App.tsx with our AuthProvider */}
    <AuthProvider> 
      <App />
    </AuthProvider>
  </StrictMode>,
)
