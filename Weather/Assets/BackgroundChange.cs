using UnityEngine;
using System.Collections;

public class BackgroundChange : MonoBehaviour {
    public GameObject background;
    public GameObject obj;
    private bool inCamera;
	// Use this for initialization
	void Start () {
	    
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKey(KeyCode.Space))
        {
            if (obj.transform.position.x >= -30)
                inCamera = true;
            else
                inCamera = false;
        }
        if (obj.transform.position.x <= -33 && inCamera)
        {
            background.GetComponent<SpriteRenderer>().sortingLayerName = "Background2";
            GetComponent<SpriteRenderer>().sortingLayerName = "Background1";
        } 
    }
}
