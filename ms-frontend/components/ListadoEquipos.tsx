import { revalidateTag } from "next/cache";
import { columns } from "./Columns";
import { DataTable } from "./DataTable";

export default async function ListadoEquipos() {
  const response = await fetch("http://ms-gateway:8080/api/equipos", {next: { revalidate: 3600, tags: ['equipos']  }});
  const rawResponse = await response.json();
  const equipos = rawResponse.equipos;    

  const response2 = await fetch("http://ms-gateway:8080/api/devoluciones/estados", {next: { revalidate: 3600, tags: ['estados']  }});
  const rawResponse2 = await response2.json();  
  const estados = rawResponse2.estados.map((estado:any) => ({id: estado.id, texto: estado.descripcion}));
  
  console.log(estados);

  return(    
      <DataTable columns={columns} data={equipos} estados={estados}/>    
  )
}