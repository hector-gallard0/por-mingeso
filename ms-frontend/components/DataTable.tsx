"use client"
 
import {
  ColumnDef,
  ColumnFiltersState,
  flexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getPaginationRowModel,
  useReactTable,
} from "@tanstack/react-table"
 
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import { Button } from "./ui/button"
import { createContext, useContext, useMemo, useState } from "react"
import { Input } from "./ui/input"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select"
import { FormLabel } from "./ui/form"
import { Label } from "./ui/label"
import EstadoDevolucion from "@/types/EstadoDevolucion"
import { FormItem } from "@/types/FormTypes"
import EstadosContext from "./context"
 
interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[]
  data: TData[]
  estados: FormItem[]
}


export function DataTable<TData, TValue>({
  columns,
  data,
  estados
}: DataTableProps<TData, TValue>) {
  const [columnFilters, setColumnFilters] = useState<ColumnFiltersState>(
    []
  )
  const [filterColumn, setFilterColumn] = useState<string>("id");
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    onColumnFiltersChange: setColumnFilters,
    getFilteredRowModel: getFilteredRowModel(),
    state:{
      columnFilters,
    }
  })

  

  useMemo(() => {
    table.setPageSize(5);
  }, [table])
 
  function handleFilterChange(filterColumn: string) {    
    table.setColumnFilters([]);
    setFilterColumn(filterColumn);    
  }

  function handleSearchChange(event: any) {    
    table.getColumn(filterColumn)?.setFilterValue(event.target.value)
  }

  return (
    <div className="w-full">
      <div className="flex items-center py-4">
        <div className="w-full">
          <Label>Buscar por</Label>
          <div className="flex gap-3">
            <div className="w-1/3">
              <Select onValueChange={handleFilterChange}>          
                <SelectTrigger>
                  <SelectValue placeholder="Seleccionar filtro" />
                </SelectTrigger>          
                <SelectContent>
                  <SelectItem               
                    value="marca"
                    >
                    Marca
                  </SelectItem>                    
                  <SelectItem               
                    value="tipo"
                    >
                    Tipo
                  </SelectItem>                    
                  <SelectItem               
                    value="id"
                    >
                    ID
                  </SelectItem>                    
                </SelectContent>
              </Select>          
            </div>
            <Input              
              placeholder="Ingresar busqueda"
              value={(table.getColumn(filterColumn)?.getFilterValue() as string) || ""}
              onChange={handleSearchChange}              
            />    
          </div>
        </div>
      </div>
      <div className="rounded-md border">
        <Table>
          <TableHeader>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => {
                  return (
                    <TableHead key={header.id}>
                      {header.isPlaceholder
                        ? null
                        : flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                    </TableHead>
                  )
                })}
              </TableRow>
            ))}
          </TableHeader>
          <EstadosContext.Provider value={estados}>
            <TableBody>
              {table.getRowModel().rows?.length ? (
                table.getRowModel().rows.map((row) => (
                  <TableRow
                    key={row.id}
                    data-state={row.getIsSelected() && "selected"}
                    >
                    {row.getVisibleCells().map((cell) => (
                      <TableCell key={cell.id}>
                        {flexRender(cell.column.columnDef.cell, cell.getContext())}
                      </TableCell>
                    ))}
                  </TableRow>
                ))
                ) : (
                  <TableRow>
                  <TableCell colSpan={columns.length} className="h-24 text-center">
                    No results.
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </EstadosContext.Provider>
        </Table>
      </div>
      <div className="flex items-center justify-end space-x-2 py-4">
        <Button
          variant="outline"
          size="sm"
          onClick={() => table.previousPage()}
          disabled={!table.getCanPreviousPage()}
        >
          Anterior
        </Button>
        <Button
          variant="outline"
          size="sm"
          onClick={() => table.nextPage()}
          disabled={!table.getCanNextPage()}
        >
          Siguiente
        </Button>
      </div>
    </div>
  )
}
