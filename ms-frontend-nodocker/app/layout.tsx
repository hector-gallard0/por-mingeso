import type { Metadata } from 'next'
import './globals.css'
import { Inter as FontSans } from "next/font/google"
import { cn } from "@/lib/utils"
import { Navbar } from '@/components/navbar'

export const fontSans = FontSans({
  subsets: ["latin"],
  variable: "--font-sans",
})

export const metadata: Metadata = {
  title: 'Gestión Préstamos DIINF',
  description: 'Plataforma para la gestión de préstamos de equipos del DIINF (Departamento de Informática de la Universidad de Santiago de Chile',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="es">
      <body
        className={cn(
          "min-h-screen bg-background font-sans antialiased",
          fontSans.variable
        )}
      >      
      <div className='flex flex-col justify-center items-center w-screen'>
        <div className='max-w-screen-xl w-full'>          
          <Navbar />
          {children}
        </div>
      </div>
      </body>
    </html>
  )
}
