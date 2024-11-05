package br.com.miaujuda.dtos;

import java.io.Serializable;

public class PetsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String txPet;        
    private String txSx;         
    private String endereco;     
    private String txObs;       
    private String txStatus;       
    private String petImage;    
    private Long userId;        

    // Getters e Setters

    public String getTxPet() {
        return txPet;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTxPet(String txPet) {
        this.txPet = txPet;
    }

    public String getTxSx() {
        return txSx;
    }

    public void setTxSx(String txSx) {
        this.txSx = txSx;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTxObs() {
        return txObs;
    }

    public void setTxObs(String txObs) {
        this.txObs = txObs;
    }
    
    public String getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}

	public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "PetsDTO [id=" + id + ", txPet=" + txPet + ", txSx=" + txSx + ", endereco=" + endereco + ", txObs="
				+ txObs + ", txStatus=" + txStatus + ", petImage=" + petImage + ", userId=" + userId + "]";
	}

    

    
}
