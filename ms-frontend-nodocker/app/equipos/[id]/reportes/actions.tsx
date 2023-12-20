"use server"

import { revalidateTag } from "next/cache";

export default async function revalidateReporte(idEquipo:number) {
  "use server"  
  revalidateTag('reportes' + idEquipo.toString());
  return;
}