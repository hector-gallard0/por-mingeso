import Marca from "./Marca"
import TipoEquipo from "./TipoEquipo"
import Uso from "./Usos"

type Equipo = {
  id: number,
  disponible: boolean,
  marca: Marca,
  tipo: TipoEquipo,
  usos: Uso[]
}

export default Equipo;