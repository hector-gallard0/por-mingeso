import FormularioProfesor from "@/components/FormularioProfesor";
import Title from "@/components/title";

export default function RegistrarProfesoresPage() {
  return(
    <div className="flex flex-col items-center gap-5">
      <Title title="Registrar profesor"/>
      <main className="w-full flex justify-center">
          <FormularioProfesor />
      </main>
    </div>
  )
}