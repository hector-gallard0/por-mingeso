import { reporteColumns } from "@/components/ReporteColumns"
import { TablaReporte } from "@/components/TablaReporte"
import Title from "@/components/title"
import { Button } from "@/components/ui/button"
import { revalidateTag } from "next/cache"
import Link from "next/link"

type Params = {
    id: string
}


export default async function ReporteEquipoPage({params}:{params: Params} ){
  const response = await fetch(`http://localhost:8080/api/reportes/prestamos?idEquipo=${params.id}`, {next: { revalidate: 3600, tags: ['reportes' + params.id] }});
  const rawResponse = await response.json();
  console.log(rawResponse);
  revalidateTag("reportes" + params.id);

  return (
    <div className="flex flex-col items-center gap-5">      
    <div className="grid grid-cols-3 gap-4 w-full max-w-screen-md">
      <div className="col-span-1">
          <Link href="/equipos">
            <Button variant="outline">
              Volver
            </Button>
          </Link>
      </div>
      <div className="col-span-2">
        <Title title="Reporte préstamos"/>
      </div>        
    </div>
    <main className="w-full flex justify-center">
        <TablaReporte columns={reporteColumns} data={rawResponse.reporte}/>
    </main>
  </div>
  )
}