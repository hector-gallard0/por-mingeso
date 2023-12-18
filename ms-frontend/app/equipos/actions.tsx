"use server"

import { revalidateTag } from "next/cache";

export default async function revalidateEquipos() {
  "use server"
  console.log("Make revalidation equipos")
  revalidateTag('equipos');
  return;
}