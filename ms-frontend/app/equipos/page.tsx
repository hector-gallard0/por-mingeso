import ListadoEquipos from "@/components/ListadoEquipos";
import Title from "@/components/title";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import Link from "next/link";

export default function EquiposPage() {
  return(    
    <div className="flex flex-col items-center gap-5">      
      <Title title="Gestionar equipos"/>
      <main className="w-full flex flex-col justify-center">   
        <div className="flex justify-start w-full">
          <Link href="/equipos/ingresar"><Button size="lg" variant="outline" className="bg-teal-500 text-white hover:bg-teal-400 hover:text-teal-950 hover:drop-shadow-md font-bold uppercase tracking-wide"><Plus/> Ingresar nuevo equipo</Button></Link>
        </div>           
        <ListadoEquipos />          
      </main>
    </div>
  )
}