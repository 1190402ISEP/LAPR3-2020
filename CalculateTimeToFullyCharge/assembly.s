.section .data

.section .text


    .global calculate_time_to_fully_charge  
    .global calculateResto

calculate_time_to_fully_charge:
    #prologue
    pushl %ebp 
    movl %esp, %ebp
    
    pushl %ebx
    ################

    movl 8(%ebp), %eax       
    movl 12(%ebp), %ecx      
    movl 16(%ebp), %ebx      

                            
    cdq
    imull %ecx	            
    movl $100, %ecx
    idivl %ecx           
    idivl %ebx           

    ###############
end:
    popl %ebx
    #epilogue
    movl %ebp, %esp 
    popl %ebp

    ret


calculateResto:
    #prologue
    pushl %ebp 
    movl %esp, %ebp
    
    pushl %ebx
    ################

    movl 8(%ebp), %eax       
    movl 12(%ebp), %ecx      
    movl 16(%ebp), %ebx      

                            
    cdq
    imull %ecx	            
    movl $100, %ecx
    idivl %ecx           
    idivl %ebx 
    movl %edx, %eax
    movl $100, %ecx 
    imull %ecx
    idivl %ebx
    cmpl $10, %eax
    jl putzero
    jmp end2

putzero:
    movl $0, %eax
	
        

    ###############
end2:
    popl %ebx
    #epilogue
    movl %ebp, %esp 
    popl %ebp

    ret

