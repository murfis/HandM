package com.handm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.handm.model.Execution;
import com.handm.model.ExecutionRepository;
import com.handm.model.ListToSort;

@Controller
public class HandMController {

	@Autowired
	private ExecutionRepository repository;

	@RequestMapping("/init")
	public String initForm(Model model) {
		model.addAttribute("listToSort", new ListToSort());
		ArrayList<Execution> executions = repository.findAll();

		model.addAttribute("previousExecutions", executions);
		return "init";
	}

	@PostMapping("/init")
	public String initSubmit(@ModelAttribute ListToSort listToSort) {
		ArrayList<String> str = new ArrayList<String>(Arrays.asList(listToSort.getNumbers().split(",")));
		ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
		Iterator<String> it = str.iterator();
		while (it.hasNext()) {
			String number = it.next();
			listOfNumbers.add(Integer.parseInt(number));

		}

		Execution execution = sortList(listOfNumbers);
		repository.save(execution);
		ArrayList<Execution> executions = new ArrayList<Execution>();
		executions.add(execution);
		return "redirect:/init";
	}

	private Execution sortList(ArrayList<Integer> listOfNumbers) {
		
		System.out.println("sorting list");
		Execution execution = new Execution();
		Random randomGenerator = new Random();
		Date start = new Date();
		Long howManyChanges = 0L;
		////////////////////////////////////////////////
		while(!isArrayOrdered(listOfNumbers)){
			int random1 = randomGenerator.nextInt(listOfNumbers.size());
			int random2 = randomGenerator.nextInt(listOfNumbers.size());
			if(random1<random2){
				if(listOfNumbers.get(random1)>listOfNumbers.get(random2)){
					int tmp = listOfNumbers.get(random1);
					listOfNumbers.set(random1, listOfNumbers.get(random2));
					listOfNumbers.set(random2,tmp);
					howManyChanges ++;
				}
			}if(random2<random1){
				if(listOfNumbers.get(random2)>listOfNumbers.get(random1)){
					int tmp = listOfNumbers.get(random2);
					listOfNumbers.set(random2, listOfNumbers.get(random1));
					listOfNumbers.set(random1,tmp);
					howManyChanges ++;
				}
			}
			System.out.println(listOfNumbers.toString());
		}
		/////////////////////////////////////////////////
		
		Date finish = new Date();
		Long howLong= ((finish.getTime() - start.getTime()));
		execution.setHowLong(howLong + " Milisecs");
		execution.setHowManyChanges(howManyChanges+ " Changes");
		execution.setLastExecution(start.toLocaleString());
		execution.setSortedList(listOfNumbers.toString());
		repository.save(execution);
		return  execution;
		
	}

	private boolean isArrayOrdered(ArrayList<Integer> listOfNumbers) {
		Iterator<Integer> it = listOfNumbers.iterator();
		int prev = it.next();
		while(it.hasNext()){
			int current= it.next();
			if(prev > current)
				return false;
			prev= current;
		}
		return true;
	}
}