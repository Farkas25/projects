using UnityEngine;
using System.Collections;

public class CloudMovement : MonoBehaviour {
    private Vector3 mousePosition;
    private float positionZ;
	// Use this for initialization
	void Start () {
        positionZ = 10;
	}
	
	// Update is called once per frame
	
    
    public void OnMouseDrag()
    {
        mousePosition.Set(Input.mousePosition.x, Input.mousePosition.y, positionZ);
        transform.position = Camera.main.ScreenToWorldPoint(mousePosition);
    } 

}
