"use client"
 
import Equipo from "@/types/Equipo"
import { ColumnDef } from "@tanstack/react-table"

export const reporteColumns: ColumnDef<Equipo>[] = [
  {
    accessorKey: "fechaPrestamo",
    header: () => <div className="text-center">Fecha préstamo</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("fechaPrestamo")}</div>,
  },
  {
    accessorKey: "horaPrestamo",
    header: () => <div className="text-center">Hora préstamo</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("horaPrestamo")}</div>,
  },
  {
    accessorKey: "fechaDevolucion",
    header: () => <div className="text-center">Fecha devolución</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("fechaDevolucion")}</div>,
  },
  {
    accessorKey: "horaDevolucion",
    header: () => <div className="text-center">Hora devolución</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("horaDevolucion")}</div>,
  },
  {
    accessorKey: "rutProfesor",
    header: () => <div className="text-center">RUT profesor</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("rutProfesor")}</div>,
  },
  {
    accessorKey: "nombreProfesor",
    header: () => <div className="text-center">Nombre profesor</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("nombreProfesor")}</div>,
  },
  {
    accessorKey: "apellidoProfesor",
    header: () => <div className="text-center">Apellido Profesor</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("apellidoProfesor")}</div>,
  },
  {
    accessorKey: "duracionPrestamo",
    header: () => <div className="text-center">Duración Préstamo</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("duracionPrestamo")}</div>,
  },
  {
    accessorKey: "estado",
    header: () => <div className="text-center">Estado equipo</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("estado")}</div>,
  },
  {
    accessorKey: "uso",
    header: () => <div className="text-center">Uso</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("uso")}</div>,
  },
]
