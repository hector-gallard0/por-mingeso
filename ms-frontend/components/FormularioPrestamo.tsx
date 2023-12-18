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
import { useMemo } from "react";
import { Textarea } from "./ui/textarea";


const FormSchema = z.object({
  rutProfesor: z
    .string({required_error: "Debe ingresar su rut.",}),
  idUso: z
    .string({required_error: "Debe seleccionar un uso.",}), 
  idEquipo: z
    .string({required_error: "Debe seleccionar un equipo.",}),
  descripcion: z
    .string()
    .max(255, {message: "La descripción no puede tener más de 255 caracteres."})
})


export default function FormularioPrestamo({equipo, ingresarPrestamo, handleCancel}:{equipo:Equipo, ingresarPrestamo: Function, handleCancel: Function}) {
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      rutProfesor: "",
      idUso: "1",
      idEquipo: equipo.id.toString(),
      descripcion: ""
    },
  })

  const usos = useMemo(() => {
    return equipo.usos.map(uso => ({id: uso.id, texto: uso.descripcion}));
  }, [equipo])

  async function onSubmit(data: z.infer<typeof FormSchema>) {    
    ingresarPrestamo(data);
  }

  return (        
    <Form {...form}>
      <form className="w-full flex flex-col gap-3">              
        <FormField
          control={form.control}
          name="rutProfesor"
          render={({ field }) => (
              <FormItem>
                <FormLabel>RUT Profesor (sin puntos y con guión) </FormLabel>
                <FormControl>
                  <Input placeholder="Ingresar RUT" {...field} />
                </FormControl>                
                <FormMessage />
              </FormItem>              
            )}
        />               
        <FormField
            control={form.control}
            name="idUso"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Para qué será usado el equipo</FormLabel>
                  <FormSelect items={usos} onChange={field.onChange} defaultValue={field.value} placeholder="Seleccione un uso"/>
                <FormMessage />
              </FormItem>
            )}
          />  
          <FormField
          control={form.control}
          name="descripcion"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Descripción del préstamo</FormLabel>
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