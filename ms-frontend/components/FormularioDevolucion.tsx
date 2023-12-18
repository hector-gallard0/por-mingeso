"use client";

import { z } from "zod";
import { Button } from "./ui/button";
import { Card, CardContent, CardDescription, CardFooter, CardHeader } from "./ui/card";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "./ui/form";
import { Input } from "./ui/input";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import FormSelect from "./FormSelect";
import Equipo from "@/types/Equipo";
import { useContext, useMemo } from "react";
import { Textarea } from "./ui/textarea";
import EstadosContext from "./context";


const FormSchema = z.object({
  idEstado: z
    .string({required_error: "Debe seleccionar un estado.",}), 
  idEquipo: z
    .string({required_error: "Debe seleccionar un equipo.",}),
  descripcion: z
    .string()
    .max(255, {message: "La descripción no puede tener más de 255 caracteres."})
})


export default function FormularioDevolucion({equipo, ingresarDevolucion, handleCancel}:{equipo:Equipo, ingresarDevolucion: Function, handleCancel: Function}) {
  const estados = useContext(EstadosContext);  
  
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {      
      idEquipo: equipo.id.toString(),
      descripcion: "",
    },
  })

  console.log("formularo devoliucon ", estados);

  async function onSubmit(data: z.infer<typeof FormSchema>) {    
    ingresarDevolucion(data);
  }

  return (        
    <Form {...form}>
      <form className="w-full flex flex-col gap-3">                 
        <FormField
            control={form.control}
            name="idEstado"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Estado equipo</FormLabel>
                  <FormSelect items={estados} onChange={field.onChange} defaultValue={field.value} placeholder="Seleccione un estado"/>
                <FormMessage />
              </FormItem>
            )}
          />  
          <FormField
          control={form.control}
          name="descripcion"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Descripción de la devolución</FormLabel>
                <FormControl>
                  <Textarea
                    placeholder="Escriba acá una descripción, es opcional."
                    className="resize-none"
                    {...field}
                  />            
                </FormControl>              
              <FormMessage />
            </FormItem>
          )}          
        />          
      </form>
      <div className="w-full flex justify-end gap-3">          
          <Button variant="destructive" className="w-fit max-w-screen-md min-w-fit" onClick={() => handleCancel()}>Cancelar</Button>          
          <Button className="w-fit max-w-screen-md min-w-fit" onClick={form.handleSubmit(onSubmit)}>Ingresar</Button>          
      </div>
    </Form>
  );
}