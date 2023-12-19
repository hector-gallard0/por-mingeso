import FormularioEquipo from "@/components/FormularioEquipo";
import Title from "@/components/title";
import { Button } from "@/components/ui/button";
import Catalogo from "@/types/Catalogo";
import { FormItems } from "@/types/FormTypes";
import Link from "next/link";

export default async function IngresarEquiposPage() {
  //revalidar cada 1 hora, solo por si acaso.
  const response = await fetch("http://localhost:8080/api/catalogo", {next: { revalidate: 3600 }});
  if(response.status !== 200) throw new Error("Failed to fetch catalogo");
  const rawResponse = await response.json();
  const catalogo:Catalogo = rawResponse.catalogo;

  return(    
    <div className="flex flex-col items-center gap-5">      
      <div className="grid grid-cols-3 gap-4 w-full max-w-screen-md">
        <div className="col-span-1">
            <Link href="/equipos">
              <Button variant="outline">
                Volver
              </Button>
            </Link>
        </div>
        <div className="col-span-1">
          <Title title="Ingresar equipos"/>
        </div>        
      </div>
      <main className="w-full flex justify-center">
          <FormularioEquipo catalogo={catalogo}/>
      </main>
    </div>
  )
}