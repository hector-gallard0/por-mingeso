"use client"
 
import Equipo from "@/types/Equipo"
import { ColumnDef } from "@tanstack/react-table"
import AccionPrestamo from "./ModalTransaccion"
import AccionEquipo from "./AccionEquipo"
import { Badge } from "./ui/badge"

export const columns: ColumnDef<Equipo>[] = [
  {
    accessorKey: "id",
    header: () => <div className="text-center">ID</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("id")}</div>,
  },
  {
    accessorFn: (row) => row.marca.nombre,
    accessorKey: "marca",
    header: () => <div className="text-center">Marca</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("marca")}</div>,
  },
  {
    accessorFn: (row) => row.disponible,
    accessorKey: "disponible",
    header: () => <div className="text-center">Disponible</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("disponible") ? (
      <Badge className="bg-green-500 hover:bg-green-600">Disponible</Badge>
    ) : (
      <Badge className="bg-red-500 hover:bg-red-600">Ocupado</Badge>
    )}</div>,
  },
  {
    accessorFn: (row) => row.tipo.nombre,
    accessorKey: "tipo",
    header: () => <div className="text-center">Tipo</div>,    
    cell: ({ row }) => <div className="text-center">{row.getValue("tipo")}</div>,
  },
  {
    id: "actions",
    header: () => <div className="text-center">Acciones</div>,
    cell: ({ row }) => {
      const equipo = row.original
      return(
        <div className="flex justify-center">
          <AccionEquipo equipo={equipo}/>        
        </div>              
      )

    }
  }
]
