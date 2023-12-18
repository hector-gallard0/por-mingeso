import FormularioPrestamo from "@/components/FormularioPrestamo";
import SeleccionarEquipo from "@/components/ListadoEquipos";
import Title from "@/components/title";

export default function IngresarPrestamosPage() {
  return(
    <div className="flex flex-col items-center gap-5">
      <Title title="Ingresar préstamo"/>
      <main className="w-full flex justify-center">
          <SeleccionarEquipo />
          {/* <FormularioPrestamo /> */}
      </main>
    </div>
  )
}