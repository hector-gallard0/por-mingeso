"use client";

import { zodResolver } from "@hookform/resolvers/zod"
import { ControllerRenderProps, useForm } from "react-hook-form"
import * as z from "zod"
import { Form, FormControl, FormDescription, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader } from "@/components/ui/card"
import Catalogo from "@/types/Catalogo";
import FormSelect from "./FormSelect";
import { useMemo } from "react";
import { Input } from "./ui/input";
import { Checkbox } from "./ui/checkbox";
import { Alert, AlertDescription, AlertTitle } from "./ui/alert";
import { Computer, Terminal } from "lucide-react";
import revalidateEquipos from "@/app/equipos/actions";

const FormSchema = z.object({
  idMarca: z.string({
      required_error: "Debe elegir una marca.",
    })
    .min(1, { message: "Debes elegir una marca." }),
  idTipo: z.string({
    required_error: "Debe elegir un tipo de equipo.",
  })
  .min(1, { message: "Debes elegir una marca." }),
  cantidadEquipos: z.string({
    required_error: "Debe ingresar la cantidad de equipos.",
  })
  .min(1, {message: "La cantidad debe equipos ser mayor a 0."}),
  idsUsos: z.array(z.number()).refine((value) => value.some((item) => item), {
    message: "Debes seleccionar al menos un permiso para este/os equipo/s.",
  }),
})

export default function FormularioEquipo({catalogo}:{catalogo:Catalogo}) {
  const items = useMemo(() => {
    return {
      marcas: catalogo.marcas.map(marca => ({id: marca.id, texto: marca.nombre})),
      tipos: catalogo.tipos.map(tipo => ({id: tipo.id, texto: tipo.nombre})),
      usos: catalogo.usos.map(uso => ({id: uso.id, texto: uso.descripcion})) 
    }
  }, [catalogo]);

  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      cantidadEquipos: "1",
      idsUsos: [1]
    },
  })
  
  async function onSubmit(data: z.infer<typeof FormSchema>) {

    if(!confirm("¿Está seguro que desea ingresar el/los equipo/s?")) return;

    const body = {
      idMarca: parseInt(data.idMarca),
      idTipo: parseInt(data.idTipo),
      cantidadEquipos: parseInt(data.cantidadEquipos),
      idsUsos: data.idsUsos 
    }

    console.log(body);

    const response = await fetch("http://localhost:8081/api/equipos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    })

    const rawResponse = await response.json();
    
    if(rawResponse.status == 200) {
      alert("Equipo/s ingresado/s exitósamente.");
      revalidateEquipos();      
      form.reset();
    }
  }

  return(
    <div className="w-full max-w-screen-md">      
      <Card>      
        <CardHeader>
          <h2 className="text-2xl font-medium text-slate-900">Datos equipo</h2>
          <CardDescription>Seleccione los datos del equipo, la cantidad que desea ingresar y luego seleccione el botón "INGRESAR"</CardDescription>
        </CardHeader>
        <CardContent>
        <Form {...form}>
          <form className="w-full flex flex-col gap-3">
            <FormField
              control={form.control}
              name="idMarca"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Marca</FormLabel>
                  <FormSelect items={items.marcas} onChange={field.onChange} defaultValue={field.value} placeholder="Seleccione una marca"/>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="idTipo"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Tipo</FormLabel>
                  <FormSelect items={items.tipos} onChange={field.onChange} defaultValue={field.value} placeholder="Seleccione un tipo"/>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="cantidadEquipos"
              render={({ field }) => (
                  <FormItem>
                    <FormLabel>Número de equipos</FormLabel>
                    <FormControl>
                      <Input type="number" placeholder="Número de equipos a ingresar" {...field} />
                    </FormControl>                
                    <FormMessage />
                  </FormItem>
                )}
            />
                <FormField
                control={form.control}
                name="idsUsos"
                render={() => (                
                  <FormItem>      
                    <FormLabel>Permisos de uso</FormLabel>                             
                    {items.usos.map((uso) => (
                      <FormField
                        key={uso.id}
                        control={form.control}
                        name="idsUsos"
                        render={({ field }) => {
                          return (
                            <FormItem
                              key={uso.id}
                              className="flex flex-row items-start space-x-3 space-y-0"
                            >
                              <FormControl>
                                <Checkbox
                                  checked={field.value?.includes(uso.id)}
                                  onCheckedChange={(checked) => {
                                    return checked
                                      ? field.onChange([...field.value, uso.id])
                                      : field.onChange(
                                          field.value?.filter(
                                            (value) => value !== uso.id
                                          )
                                        )
                                  }}
                                />
                              </FormControl>
                              <FormLabel className="font-normal">
                                {uso.texto}
                              </FormLabel>
                            </FormItem>
                          )
                        }}
                      />
                    ))}
                    <FormMessage />
                  </FormItem>
                )}
              />          
          </form>
        </Form>
        </CardContent>
        <CardFooter className="flex justify-center">
          <Button className="w-full max-w-screen-md min-w-fit" onClick={form.handleSubmit(onSubmit)} type="submit">Ingresar</Button>
        </CardFooter>
      </Card>
    </div>
  )
}