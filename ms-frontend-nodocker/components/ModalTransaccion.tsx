"use client";

import Equipo from "@/types/Equipo";
import { ArrowRightCircle } from "lucide-react";
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle, AlertDialogTrigger } from "./ui/alert-dialog";
import FormularioPrestamo from "./FormularioPrestamo";
import { ReactElement, ReactNode, useState } from "react";
import { Button } from "./ui/button";

export default function ModalTransaccion({title, equipo, children, open, setOpen}:{title:string; equipo:Equipo, children: ReactNode, open: boolean, setOpen: (open: boolean) => void}) {
  return(    
    <AlertDialog onOpenChange={setOpen} open={open}>        
    <AlertDialogContent>
      <AlertDialogHeader>
        <AlertDialogTitle>{title}</AlertDialogTitle>
        <h2 className="text-md text-amber-600 font-semibold">{equipo.tipo.nombre} {equipo.marca.nombre} {equipo.id}</h2>
      </AlertDialogHeader>                                    
      {children}
    </AlertDialogContent>
  </AlertDialog>        
  )
}
