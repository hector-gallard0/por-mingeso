import { FormItems } from "./FormTypes";
import Marca from "./Marca";
import TipoEquipo from "./TipoEquipo";
import Uso from "./Usos";

type Catalogo = {
  marcas: Marca[];
  tipos: TipoEquipo[];
  usos: Uso[];
}

export default Catalogo;