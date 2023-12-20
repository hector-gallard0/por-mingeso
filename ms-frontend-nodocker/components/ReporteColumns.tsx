"use client"
 
import Equipo from "@/types/Equipo"
import { ColumnDef } from "@tanstack/react-table"
import { Button } from "./ui/button"
import { ArrowUpDown, Calendar } from "lucide-react"
import { ReactNode } from "react"

const SortingButton = ( column:any, text:string ) => {
  return (
    <Button
          variant="ghost"
          size="sm"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    >       
      {text}
      <ArrowUpDown className="ml-2 h-4 w-4" />
    </Button>
  )
}

export const reporteColumns: ColumnDef<Equipo>[] = [
  {
    accessorKey: "fechaPrestamo",
    header: ({ column }) => {
      return (
        SortingButton(column, "Fecha préstamo")
      )
    },    
    cell: ({ row }) => <div className="text-center">{row.getValue("fechaPrestamo")}</div>,
  },
  {
    accessorKey: "horaPrestamo",
    header: ({ column }) => {
      return SortingButton(column, "Hora préstamo")
    },    
    cell: ({ row }) => <div className="text-center">{row.getValue("horaPrestamo")}</div>,
  },
  {
    accessorKey: "fechaDevolucion",
    header: ({ column }) => {
      return SortingButton(column, "Fecha devolución")
    },    
    cell: ({ row }) => {
      const fechaDevolucion:any = row.getValue("fechaDevolucion");
      return <div className="text-center">{fechaDevolucion != undefined ? fechaDevolucion : <span className="text-red-500 font-medium">{'Sin devolución'}</span>}</div>
    },
  },
  {
    accessorKey: "horaDevolucion",
    header: ({ column }) => {
      return SortingButton(column, "Hora devolución")
    },    
    cell: ({ row }) => {
      const horaDevolucion:any = row.getValue("fechaDevolucion");
      return <div className="text-center">{horaDevolucion != undefined ? horaDevolucion : <span className="text-red-500 font-medium">{'Sin devolución'}</span>}</div>
    },
  },
  {
    accessorKey: "rutProfesor",
    header: ({ column }) => {
      return SortingButton(column, "RUT profesor")
    }, 
    cell: ({ row }) => <div className="text-center">{row.getValue("rutProfesor")}</div>,
  },
  {
    accessorKey: "duracionPrestamo",
    header: ({ column }) => {
      return SortingButton(column, "Duración")
    }, 
    cell: ({ row }) => {
      const duracionEnHoras = Math.floor(parseInt(row.getValue("duracionPrestamo")));
      let texto = "";
      if(duracionEnHoras < 60) {
        texto += `${duracionEnHoras.toString()} segundos`
      }else if(duracionEnHoras < 3660) {
        texto += `${Math.floor(duracionEnHoras/60)} minutos`
      }else{
        texto += `${Math.floor(duracionEnHoras/3600)} horas`
      }
      return <div className="text-center">{texto}</div>
    },
  },
  {
    accessorKey: "estado",
    header: ({ column }) => {
      return SortingButton(column, "Estado")
    },     
    cell: ({ row }) => <div className="text-center">{row.getValue("estado")}</div>,
  },
  {
    accessorKey: "uso",
    header: ({ column }) => {
      return SortingButton(column, "Uso")
    },        
    cell: ({ row }) => <div className="text-center">{row.getValue("uso")}</div>,
  },
]
