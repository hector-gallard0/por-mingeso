"use client";

import Image from "next/image";
import Link from "next/link"
import { usePathname } from "next/navigation"

const links = [
  {
    href: "/",    
    label: "Inicio",
    key: "inicio",
  },
  {
    href: "/equipos",
    label: "Equipos",
    key: "equipos",
  },
  {
    href: "/profesores/registrar",
    label: "Registrar profesor",
    key: "profesores",
  },
]

export function Navbar() {
  const pathname = usePathname();

  return(
    <nav className="px-6 pb-1 flex justify-between w-full items-center border-b-[1px] border-amber-400 mb-6">      
        <div>
          <Image src="/images/Usach SV1.png" width={ 200 } height={ 50 } alt="Logo" />
        </div>
        <div>
          <ul className="flex gap-3">
            {links.map(( link, index ) => 
              <li key={ index }>
                <Link href={ link.href }>                  
                    {pathname == "/" ? (
                      <span className={`px-3 py-2 rounded-md ${(pathname == link.href) ? "bg-teal-100 text-teal-600 font-medium hover:bg-teal-50" : "hover:bg-slate-50"}`}>{ link.label }</span>                  
                    ) : (
                      <span className={`px-3 py-2 rounded-md ${(pathname.includes(link.key)) ? "bg-teal-100 text-teal-600 font-medium hover:bg-teal-50" : "hover:bg-slate-50"}`}>{ link.label }</span>
                    )}                      
                </Link>     
              </li>
            )}
          </ul>
        </div>
    </nav>
  )
}