"use client";

import { FormItems } from "@/types/FormTypes";
import { FormControl, FormField, FormItem, FormLabel } from "./ui/form";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select";

export default function FormSelect({items, onChange, defaultValue, placeholder}:{items: FormItems;onChange: (value: string) => void, defaultValue: string | undefined, placeholder: string}) {
  return(
    <Select onValueChange={onChange} defaultValue={defaultValue}>
      <FormControl>
        <SelectTrigger>
          <SelectValue placeholder={placeholder} />
        </SelectTrigger>
      </FormControl>
      <SelectContent>
        {
          items.map((item, index) => (
            <span key={index}>
              <SelectItem value={item.id.toString()}>{item.texto}</SelectItem>                        
            </span>
          ))
        }                    
      </SelectContent>
    </Select>  
  )
}