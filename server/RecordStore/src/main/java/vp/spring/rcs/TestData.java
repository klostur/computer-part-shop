package vp.spring.rcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.UserRepository;
import vp.spring.rcs.model.Brand;
import vp.spring.rcs.model.ComputerPart;
import vp.spring.rcs.service.BrandService;
import vp.spring.rcs.service.ComputerPartService;

@Component
public class TestData {
	@Autowired
	ComputerPartService computerPartService;

	@Autowired
	BrandService brandService;
	String[] niz = {"maticnePloce"};//, "memorije","procesori"};

//	@PostConstruct
	public void init() {
		initBrands();
		initComponents();
	}
	
	public void initBrands() {
		for (int i = 0; i < niz.length; i++) {
			initBrand(niz[i]);
		}
	}
	
	public void initBrand(String string) {
		String csvFile = "/home/klostur/git/VP03_2018/56/zadatak/data/"+ string+".txt";
		BufferedReader br = null;
		String line = "";
		String[] brend = null;

		List<Brand> brendovi = new ArrayList<>();

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				brend = line.split("\\|");
				Brand b = new Brand(brend[0]);

				if (!brendovi.contains(b)) {
					brendovi.add(b);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for (Brand brand : brendovi) {
			brandService.save(brand);
		}
		

	}
	public void initComponents() {
		for (int i = 0; i < niz.length; i++) {
			initComponent(niz[i]);
		}
	}
	
	public void initComponent(String string) {
		String csvFile = "/home/klostur/git/VP03_2018/56/zadatak/data/"+ string+".txt";
		BufferedReader br = null;
		String line = "";
		String[] naziv = null;


		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				naziv = line.split("\\|");
				ComputerPart compPart = new ComputerPart(brandService.findByName(naziv[0]), naziv[1], Double.parseDouble(naziv[2]));
				computerPartService.save(compPart);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
