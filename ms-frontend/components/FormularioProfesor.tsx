"use client";

import { z } from "zod";
import { Button } from "./ui/button";
import { Card, CardContent, CardDescription, CardFooter, CardHeader } from "./ui/card";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "./ui/form";
import { Input } from "./ui/input";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";


const FormSchema = z.object({
  rut: z
    .string({required_error: "Debe ingresar su rut.",})
    .regex(/^[0-9]+-[0-9kK]{1}$/, {message: "El rut debe tener el formato 12345678-9."}),  
  nombre: z
    .string({required_error: "Debe ingresar su nombre.",})
    .regex(/^[a-zA-Z]+$/, {message: "El nombre solo puede contener letras."}),
  apellido: z
    .string({required_error: "Debe ignresar su apellido.",})  
    .regex(/^[a-zA-Z]+$/, {message: "El apellido solo puede contener letras."})
})


export default function FormularioProfesor() {

  
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      rut: "",
      nombre: "",
      apellido: ""
    },
  })

  async function onSubmit(data: z.infer<typeof FormSchema>) {
    console.log(data);
    const response = await fetch(`http://localhost:8080/api/profesores`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const rawResponse = await response.json();

    if(rawResponse.status == 200) {
      alert("Profesor registrado correctamente.");
      form.reset();
    }else{
      alert("Error al registrar profesor.");
    }
  }

  return (
    <div className="w-full max-w-screen-md">      
      <Card>      
        <CardHeader>
          <h2 className="text-2xl font-medium text-slate-900">Datos profesor</h2>
          <CardDescription>Ingrese los datos del profesor y luego seleccione el botón "REGISTRAR"</CardDescription>
        </CardHeader>
        <CardContent>
          <Form {...form}>
            <form className="w-full flex flex-col gap-3">              
              <FormField
                control={form.control}
                name="rut"
                render={({ field }) => (
                    <FormItem>
                      <FormLabel>RUT (sin puntos y con guión) </FormLabel>
                      <FormControl>
                        <Input placeholder="Ingresar RUT" {...field} />
                      </FormControl>                
                      <FormMessage />
                    </FormItem>
                  )}
              />    
              <FormField
                control={form.control}
                name="nombre"
                render={({ field }) => (
                    <FormItem>
                      <FormLabel>Nombre</FormLabel>
                      <FormControl>
                        <Input placeholder="Ingresar nombre" {...field} />
                      </FormControl>                
                      <FormMessage />
                    </FormItem>
                  )}
              />    
              <FormField
                control={form.control}
                name="apellido"
                render={({ field }) => (
                    <FormItem>
                      <FormLabel>Apellido</FormLabel>
                      <FormControl>
                        <Input placeholder="Ingresar apellido" {...field} />
                      </FormControl>                
                      <FormMessage />
                    </FormItem>
                  )}
              />             
            </form>
          </Form>
        </CardContent>
          <CardFooter className="flex justify-center">
            <Button className="w-full max-w-screen-md min-w-fit" onClick={form.handleSubmit(onSubmit)} type="submit">REGISTRAR</Button>
          </CardFooter>
      </Card>
    </div>
);
}