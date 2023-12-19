"use client";

import { MoreHorizontal } from "lucide-react";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "./ui/dropdown-menu";
import { Button } from "./ui/button";
import Equipo from "@/types/Equipo";
import ModalTransaccion from "./ModalTransaccion";
import { AlertDialog } from "@radix-ui/react-alert-dialog";
import { AlertDialogContent, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger } from "./ui/alert-dialog";
import { useContext, useState } from "react";
import FormularioPrestamo from "./FormularioPrestamo";
import revalidateEquipos from "@/app/equipos/actions";
import EstadosContext from "./context";
import FormularioDevolucion from "./FormularioDevolucion";
import Link from "next/link";

export default function AccionEquipo({equipo}:{equipo:Equipo}){
  const [openPrestamoModal, setOpenPrestamoModal] = useState(false);
  const [openDevolucionModal, setOpenDevolucionModal] = useState(false);

  async function ingresarPrestamo(data: any) {
    console.log(data);
    const response = await fetch("http://localhost:8080/api/prestamos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })

    const rawResponse = await response.json();

    revalidateEquipos();
    
    console.log(rawResponse);

    if(rawResponse.status == 200) {
      alert("Préstamo ingresado exitósamente.");
    }else{
      alert(rawResponse.message);
    }

    // if(rawResponse.message) {
    //   alert(rawResponse.message);
    // }else{
    //   alert("Préstamo ingresado exitósamente.")
    // }    
  
    setOpenPrestamoModal(false);
  }

  async function ingresarDevolucion(data: any) {
    console.log(data);
    const response = await fetch("http://localhost:8080/api/devoluciones", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })

    const rawResponse = await response.json();

    if(rawResponse.status == 200) {
      alert("Devolución ingresada exitósamente.");
    }else{
      alert(rawResponse.message);
    }

    revalidateEquipos();
    setOpenDevolucionModal(false);
  }

  return(
    <div key={equipo.id}>
      <div>
        {equipo.disponible ? (
          <Button variant="outline" size="sm" className="bg-teal-500 text-white hover:bg-teal-200 hover:text-teal-800 mb-2 w-full" onClick={() => setOpenPrestamoModal(true)}>
            Préstamo
          </Button>
        ) : (
          <Button variant="outline" size="sm" className="bg-amber-500 text-white hover:bg-amber-200 hover:text-amber-800 mb-2 w-full" onClick={() => setOpenDevolucionModal(true)}>
            Devolución
          </Button>
        )}         
        <Link href={`/equipos/${equipo.id}/reportes`}>
          <Button size="sm" variant="outline" className="bg-blue-500 text-white hover:bg-blue-200 hover:text-blue-800 w-full">Reporte</Button>       
        </Link>
      </div>
      <ModalTransaccion title={"Ingresar préstamo"} equipo={equipo} open={openPrestamoModal} setOpen={setOpenPrestamoModal}>
        <FormularioPrestamo equipo={equipo} ingresarPrestamo={ingresarPrestamo} handleCancel={() => setOpenPrestamoModal(false)}/>
      </ModalTransaccion>
      <ModalTransaccion title={"Ingresar devolución"} equipo={equipo} open={openDevolucionModal} setOpen={setOpenDevolucionModal}>
        <FormularioDevolucion equipo={equipo} ingresarDevolucion={ingresarDevolucion} handleCancel={() => setOpenDevolucionModal(false)}/>
      </ModalTransaccion>
    </div>
  )
}