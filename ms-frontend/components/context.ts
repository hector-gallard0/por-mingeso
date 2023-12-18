import { FormItem, FormItems } from "@/types/FormTypes";
import { createContext } from "react";

// Crear el contexto con un valor predeterminado
const EstadosContext = createContext<FormItems>([]);

export default EstadosContext;